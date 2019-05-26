$(document).ready(function () {
    (function () {
        new FroalaEditor('textarea', {
            language: 'pt_br',
            //Images
            //Upload
            imageUploadParam: 'file',
            imageUploadURL: '/materiais/upload',
            imageUploadMethod: 'POST',
            imageMaxSize: 10 * 1024 * 1024 * 1024,
            //Load
            imageManagerLoadURL: '/materiais/load_images',
            imageManagerLoadMethod: "GET",
            //Delete
            imageManagerDeleteURL: "/materiais/delete",
            imageManagerDeleteMethod: "DELETE",
            //Video
            videoUploadParam: 'file',
            videoUploadURL: '/materiais/upload',
            videoUploadMethod: 'POST',
            videoMaxSize: 10 * 1024 * 1024 * 1024,
            //File
            fileUploadParam: 'file',
            fileUploadURL: '/materiais/upload',
            fileUploadMethod: 'POST',
            fileMaxSize: 10 * 1024 * 1024 * 1024
        })
    })()
    $('form').keypress(function (e) {
        if ((e.keyCode == 10) || (e.keyCode == 13)) {
            e.preventDefault();
        }
    });
    $('.adicionar-categorias').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var categoria = $('.adicionar-categorias').val();
            $.post(`/categorias/cadastrar/${categoria}`, function (data, status) {
                $('.adicionar-categorias').val("");
                var tem = false;
                $('.categorias').each(function (index, element) {
                    console.log(element.id)
                    console.log(`${data.id}`)
                    if (element.id == `${data.id}`) {
                        element.checked = true;
                        tem = true;
                    }
                    else {
                        console.log("diferente");
                    }
                });
                console.log(tem);
                if (!tem) {
                    console.log("adicionando categoria")
                    var categoriaTemplate = `<div class="col-sm-6 col-lg-3">` +
                        `<span class="bmd-form-group is-filled">` +
                        `<div class="checkbox">` +
                        `<label class="label-upload" for="${data.id}">` +
                        `<input class="categorias filled-in" type="checkbox" name="categorias" id="${data.id}" checked value="${data.id}">` +
                        `<span class="checkbox-decorator">` +
                        `<span class="check">` +
                        `</span>` +
                        `</span>` +
                        `${data.nome}` +
                        `</label>` +
                        `</div>` +
                        `</span>` +
                        `</div>`
                    console.log(categoriaTemplate)
                    console.log($(".categorias-carregar"))
                    $(".categorias-carregar").append(categoriaTemplate);
                }
            });
        }
    });
    $.get("/tags").done(function (data) {
        var tags = [];

        for (var x in data) {
            tags.push(data[x].nome);
        }

        $('input.autocomplete').autocomplete({
            source: tags
        });
    });

    $('.adicionar-tags').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            var tag = $('.adicionar-tags').val();
            $.post(`/tags/cadastrar/${tag}`, function (data, status) {
                $('.adicionar-tags').val("");
                var tem = false;
                $('.tag').each(function (index, element) {
                    if (element.id == `${data.id}`) {
                        console.log("igual");
                        tem = true;
                    }
                    else {
                        console.log("diferente");
                    }
                });
                if (!tem) {
                    var tagTemplate = `<div style="margin-left:2%;">` +
                        `<label for="${data.id}">` +
                        `${data.nome} <span class="deletar-tag" onclick="this.parentNode.parentNode.remove()" aria-hidden="true">&times;</span>` +
                        `<input class="tag filled-in" style="display:none" checked type="checkbox" name="tags" id="${data.id}" value="${data.id}">` +
                        `</label>` +
                        `</div>`
                    $(".tags-carregar").append(tagTemplate);
                }
            });
        }
    });
});