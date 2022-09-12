function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();

    if (!commentContent){
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": commentContent,
            "type": 1
        }),
        dataType: "json",
        success: function (response) {
            if (response.code == 200){
                window.location.reload();
                $ ("#comment_section").hide();
            } else {
                if (response.code == 2003){
                    var message = confirm(response.message);
                    if (message){
                        window.open("https://github.com/login/oauth/authorize?client_id=07321f78eebe9dfe4011&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        }
    });
}
