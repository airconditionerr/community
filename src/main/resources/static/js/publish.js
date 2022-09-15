function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

function showTagSelector() {
    $("#tag_selector").show();
}

$(function () {
    var editor = editormd("question-editor", {
        width: "100%",
        height: 350,
        delay: 0,
        watch: false,
        placeholder: "请输入问题描述",
        emoji: true,
        path : "/js/lib/"
    })
})
