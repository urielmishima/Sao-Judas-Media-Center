$(document).ready(function () {
    var conteudo;
    var usuario = $('.profile-user-name').get(0).id;
    var tags = '[[${tagsParam}]]'
    console.log("Id do usuario Logado: " + usuario);

    $(document).on('click', '.atribuirConteudo', function () {
        console.log("Entrou no click do atribuirConteudo. Conteudo atual: " + conteudo);
        conteudo = this.id;
        console.log("Como o conteudo está apoós a tranformação do click do atribuirConteudo: " + conteudo);
    });

    $(document).on('click', '.enviar-sugestao', function () {
        var sugestao = $('#sugestao').val();
        $.post(`/sugestoes?usuario=${usuario}&sugestao=${sugestao}&conteudo=${conteudo}`, function (data, status) {
            $('#tag-modal').modal('hide')
        });
    });

    $(document).on('click', '.enviar-feedback-positivo', function () {
        var botao = this;
        var idBotao = this.id;
        var botaoContrario = $(this).parent().parent().children('.feedback-negativo').children();
        console.log(`/feedbacks/positivo?usuario=${usuario}&conteudo=${idBotao}`)
        $.post(`/feedbacks/positivo?usuario=${usuario}&conteudo=${idBotao}`, function (data, status) {
            console.log("Id do botão clicado : " + botao.id);
            $(botao).children('.icon').css("color", "#353c86")
            botaoContrario.children('.icon').css("color", "#6c757d")
        });
    });

    $(document).on('click', '.enviar-feedback-negativo', function () {
        var botao = this;
        var idBotao = this.id;
        var botaoContrario = $(this).parent().parent().children('.feedback-positivo').children();
        console.log(`/feedbacks/negativo?usuario=${usuario}&conteudo=${idBotao}`)
        $.post(`/feedbacks/negativo?usuario=${usuario}&conteudo=${idBotao}`, function (data, status) {
            console.log("Id do botão clicado : " + botao.id);
            $(botao).children('.icon').css("color", "#353c86");
            botaoContrario.children('.icon').css("color", "#6c757d")
        });
    });
    function getLikeAndDeslike() {
        if (usuario.length > 0) {
            $.get(`/feedbacks/positivo/usuario/?usuario=${usuario}`, function (data, status) {
                console.log("Entrou no histórico de feedbacks positivos")
                var feedbacks = data;
                $('.enviar-feedback-positivo').each(function (index) {
                    if (feedbacks.includes(this.id)) {
                        $(this).children('.icon').css("color", "#353c86");
                    }
                });
            });
            $.get(`/feedbacks/negativo/usuario/?usuario=${usuario}`, function (data, status) {
                console.log("Entrou no histórico de feedbacks negativos")
                var feedbacks = data;
                $('.enviar-feedback-negativo').each(function (index) {
                    if (feedbacks.includes(this.id)) {
                        $(this).children('.icon').css("color", "#353c86");
                    }
                });
            });
        }
    }
    getLikeAndDeslike();

    $('.buscaCategoria').click(function () {
        id = this.id;
        $.get(`/categorias/conteudo/${id}/?tagsParam=${tags}`, function (data, status) {
            var template = `<div class="row">
                <div class="col-sm-12 col-lg-12">`;
            for (x in data) {
                var caraDaVez = data[x];
                console.log(caraDaVez)

                var htmlDaVez = `<div class="card mt-4">
                                    <div class="card-header">
                                        <a class="card-link" href="#">
                                            <h5 class="card-title mb-4">${caraDaVez.titulo}</h5>
                                        </a>
                                        <p class="card-text">${caraDaVez.descricao}</p>
                                        <small class="text-muted">${caraDaVez.data}</small>
                                    </div>
                                    <div class="card-body">
                                        <div class="content fr-view">${caraDaVez.conteudo}</div>
                                        <div class="post-tags mt-2">`;

                for (z in caraDaVez.tags) {
                    tagDaVez = caraDaVez.tags[z];
                    htmlDaVez = htmlDaVez.concat(`<span class="badge badge-primary">${tagDaVez.nome}</span>`);
                }

                htmlDaVez = htmlDaVez.concat(`</div></div>`);
                if (usuario.length > 0) {
                    htmlDaVez = htmlDaVez.concat(`<div class="card-footer">
                                        <div class="row">
                                            <div class="col-4 feedback-positivo">
                                                <button type="button" id="`+ caraDaVez.id + `"
                                                    class="atribuirConteudo enviar-feedback-positivo btn btn-secondary bmd-btn"
                                                    style="width: 100%">
                                                    <i class="material-icons icon">thumb_up</i>[[#{gostei}]]
                                                </button>
                                            </div>
                                            <div class="col-4 feedback-negativo">
                                                <button type="button" id="`+ caraDaVez.id + `"
                                                    class="atribuirConteudo enviar-feedback-negativo btn btn-secondary bmd-btn"
                                                    style="width: 100%">
                                                    <i class="material-icons icon">thumb_down</i>[[#{nao-gostei}]]
                                                </button>
                                            </div>
                                            <div class="col-4">
                                                <button id="`+ caraDaVez.id + `" type="button"
                                                    class="atribuirConteudo btn btn-secondary bmd-btn"
                                                    style="width: 100%" data-toggle="modal"
                                                    data-target="#tag-modal">
                                                    <i class="material-icons icon">comment</i>
                                                    [[#{sugerir-tag}]]
                                                </button>
                                            </div>
                                        </div>
                                    </div>`);
                }
                template = template.concat(htmlDaVez + `</div>`);
            }
            template = template.concat(`</div></div>`);
            console.log(template)
            document.getElementById("resultadoCategoria").innerHTML = template;
            getLikeAndDeslike();
        });
    });
});