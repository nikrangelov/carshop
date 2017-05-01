$(document).ready(function() {
    $.get("public/pages/1", function(data) {
        $.each(data.page.content, function(index, obj) {
            console.log(obj);

            text='<div class="well">'+
                    '<div class="media">'+
                        '<div class="media-body">'+
                            '<h4 class="media-heading">' + obj.manufacturer + ' ' + obj.model + ' ' + obj.manufactureYear + '</h4>'+
                            '<p class="text-right">' + obj.price + '€' + '</p>'+
                            '<p>asdf</p>'+
                            '<ul class="list-inline list-unstyled">'+
                                '<li><span><i class="glyphicon glyphicon-calendar"></i> 2 days, 8 hours </span></li>'+
                '</ul></div></div></div>';
            console.log(text);
            $("#listing").append(text);

        });
    });
});
