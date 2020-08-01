/**
 *
 */

$('document').ready(function(){
    $('.table .btn').on('click', function(event){
        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(marathon, status){
            $('#idEdit').val(marathon.id);
           $('#titleEdit').val( marathon.title);
        });

        $('#editModal').modal();
    });
});