/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    comment2target(questionId, 1, commentContent);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        dataType: "json",
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    var message = confirm(response.message);
                    if (message) {
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

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#second_comment_" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment_" + id);

    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        e.removeAttribute("data-collapse");
        e.classList.remove("active")
    } else {
        var subCommentContainer = $("#comment_" + id);
        if (subCommentContainer.children().length != 1) {
            // 展开二级评论
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {




                    var mediaBodyElement = $("<div/>", {}).append($("<span/>", {
                        "class": "comment_content",
                        "html": comment.content
                    })).append($("<span/>", {
                        "class": "comment_time",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    }));

                    var mediaLeftElement = $("<div/>", {
                        "class": "d-flex media_object"
                    }).append($("<img/>", {
                        "class": "me-3 rounded avatar",
                        "src": $("#hidden_avatar_url").val()
                    })).append($("<div/>", {
                        "class": "commentor_name"
                    }).append($("<h7/>", {
                        "class": "fw-bold",
                        "html": comment.user.name
                    })));


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-12 second_comment"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);


                    subCommentContainer.prepend(commentElement);
                });


                console.log(data);
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}
