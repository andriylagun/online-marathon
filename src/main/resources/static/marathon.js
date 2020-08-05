/**
 *
 */

$('document').ready(function(){
    $('.table .btn-primary').on('click', function(event){
        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(tempMarathon, status){
            $('#idEdit').val(tempMarathon.id);
            $('#titleEdit').val(tempMarathon.title);
        });

        $('#editModal').modal();
    });
});