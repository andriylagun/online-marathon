/**
 *
 */

$('document').ready(function(){
    $('.table .btn-primary').on('click', function(event){
        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(student, status){
            $('#idEdit').val(student.id);
            $('#firstNameEdit').val(student.firstName);
            $('#lastNameEdit').val(student.lastName);
            $('#emailEdit').val(student.email);
            $('#passwordEdit').val(student.password);
        });

        $('#editModal').modal();
    });
});