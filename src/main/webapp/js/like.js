function Like(board_id, user_id) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "./like.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onload = function() {
        if (xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            // 응답받은 좋아요 상태에 따라 버튼 색상 변경
            if (response.like == 1) {
                document.getElementById("likeBtn").style.backgroundColor = "blue"; // 좋아요 상태
            } else {
                document.getElementById("likeBtn").style.backgroundColor = "white"; // 좋아요 취소 상태
            }
        }
    };

    // 요청 데이터 전송
    xhr.send("board_id=" + board_id + "&user_id=" + user_id);
}
