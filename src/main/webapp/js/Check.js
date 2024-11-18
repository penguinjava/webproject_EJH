function check(user_Id) {
	let messageDiv = document.getElementById('Message');

	if (user_Id.trim() === '') {
		messageDiv.textContent = '';  // 빈 입력 필드일 경우 메시지 숨기기
		return;
	}

	// AJAX 요청 생성
	let xhr = new XMLHttpRequest();
	xhr.open("GET", `./check.do?type=user_id&value=${encodeURIComponent(user_Id)}`, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			let response = JSON.parse(xhr.responseText);
		    if (response.available) {
		    	messageDiv.textContent = '사용 가능한 아이디입니다.';
		       	messageDiv.style.color = 'green';
		    } else {
		        messageDiv.textContent = '이미 사용 중인 아이디입니다.';
		        messageDiv.style.color = 'red';
		    }
		}
	};
	xhr.send();
}

// 이벤트 리스너에서 check 호출
document.getElementById('user_id').addEventListener('keyup', function() {
	let user_Id = document.getElementById('user_id').value;  // 사용자가 입력한 아이디
	check(user_Id);  // check 함수 호출
});
