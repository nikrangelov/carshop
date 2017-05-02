function createPagination(htmlTag, begin, current, end) {
    var text = '<ul class="pagination">';
    if (current == begin) {
        text += '<li class="page-item disabled"><a href="#" class="page-link" tabindex="-1">Previous</a></li>';
    } else {
        text += '<li class="page-item"><a href="#" class="page-link" onclick="openPage(' + (current - 1) + ')" tabindex="-1">Previous</a></li>';
    }
    for (i = begin; i <= end; i++) {
        if(i == current) {
            text += '<li class="page-item active"><a href="#" class="page-link" onclick="openPage(' + i + ')">' + i + '</a></li>';
        } else {
            text += '<li class="page-item"><a href="#" class="page-link" onclick="openPage(' + i + ')">' + i + '</a></li>';
        }
    }
    if (current == end) {
        text += '<li class="page-item disabled"><a href="#" class="page-link">Next</a></li>';
    } else {
        text += '<li class="page-item"><a href="#" class="page-link" onclick="openPage(' + (current + 1) + ')" >Next</a></li>';
    }
    text += '</ul>';

    $(htmlTag).html(text);
}

function openPage(pageNumber) {
    waitingDialog.show();
    $.get("public/pages/" + pageNumber, function(data) {
        console.log(data);
        var text = '';
        var postContent = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. ' +
            'unc cursus imperdiet rhoncus. Donec sollicitudin magna neque, sed dignissim ' +
            'mi maximus quis. Praesent erat augue, iaculis eu commodo non, luctus porttitor ' +
            'orci. Aliquam vel gravida ligula, sollicitudin ultrices metus. Morbi sed velit ' +
            'luctus, tristique felis vel, convallis nisi.';
        $.each(data.page.content, function(index, obj) {
            text +=
                '<div class="well">'+
                '<div class="media">'+
                '<div class="media-body">'+
                '<h4 class="media-heading">' + obj.manufacturer + ' ' + obj.model + ' ' + obj.manufactureYear + '</h4>'+
                '<p class="text-right">' + obj.price + '€' + '</p>'+
                '<p>' + postContent + '</p>'+
                '<ul class="list-inline list-unstyled">'+
                '<li><span><i class="glyphicon glyphicon-calendar"></i> 2 days, 8 hours </span></li>'+
                '</ul></div></div></div>';
        });
        var begin = data.begin;
        var current = data.current;
        var end = data.end;
        $("#listing").html(text);
        createPagination("#paginationTop", begin, current, end);
        createPagination("#paginationBottom", begin, current, end);
        waitingDialog.hide();
    });
}

$(document).ready(function() {
    openPage(1);
});
