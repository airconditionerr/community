function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
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
