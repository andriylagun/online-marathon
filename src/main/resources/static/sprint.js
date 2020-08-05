
$('document').ready(function(){
    $('.table .btn-primary').on('click', function(event){
        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(sprint,sprintMarathon, status){
            $('#idEdit').val(sprint.id);
            $('#titleEdit').val(sprint.title);
            $('#marathonIdEdit').val(sprintMarathon.id)
        });

        $('#editModal').modal();
    });
});