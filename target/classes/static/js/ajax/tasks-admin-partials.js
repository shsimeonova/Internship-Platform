// $('#taskCreateForm').submit(function (event) {
//     event.preventDefault();
//     console.log("kur")
// });
function validateCreateTaskForm() {
    console.log("validate form!");
    var values = getFormFieldValues('taskCreateForm');
    console.log(values);
}

function getFormFieldValues(formId) {
    var values = {};
    $.each($('#' + formId).serializeArray(), function(i, field) {
        values[field.name] = field.value;
    });
    return values;
}

$("#tasks").click(loadAllTasks());
function loadAllTasks() {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks',
        success: function (data) {
            $("#dynamic").html("").append(data);
        }
    });
    loadTasksByPageForAdmin(0);
}

$(".adminTasksPageBtn").click(loadTasksByPageForAdmin(page));
function loadTasksByPageForAdmin(page) {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/all/' + '?page=' + page + '&size=4&partial=true',
        success: function (data) {
            $(".dynamicPanel").html("").append(data);
        }
    });
}

$("#createTask").click(loadAllTasks());
function loadCreateTaskForm() {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/create',
        success: function (data) {
            $("#taskPanel").html("").append(data);
        }
    });
}

$("#allTasks").click(loadAllTasks());
function loadAllTasksInPanel() {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/all',
        success: function (data) {
            $("#taskPanel").html("").append(data);
        }
    });
}

$(".taskPageBtn").click(loadTasksByPage(page));
function loadTasksByPage(page) {
    console.log(page);
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/all?page=' + page + '&size=4',
        success: function (data) {
            $("#taskPanel").html("").append(data);
        }
    });
}

$("#allTaskApplications").click(loadAllTaskApplications());
function loadAllTaskApplications() {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/applications',
        success: function (data) {
            $("#taskPanel").html("").append(data);
        }
    });
}

$(".edit-btn").click(loadEditTaskForm);
function loadEditTaskForm(taskId) {
    $.ajax({
        type: 'GET',
        url: '/admin/tasks/edit/' + taskId,
        success: function (data) {
            console.log(data);
            $("#modalContent").html("").append(data);
        }
    });
}