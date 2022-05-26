window.onload = function () {

    var verifyNumber = "";
    //이메일 재인증
    document.getElementById('sendVeriNum').addEventListener('click', () => {
        var inputEmail = document.getElementById('inputEmail').value;
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = () => {
            if(httpRequest.readyState === XMLHttpRequest.DONE) {
                var requestResult = httpRequest.response;
                /* Ajax가 정상적으로 완료 되었을 때 */
                /* 중복된 Email인 경우 resultMessage만 반환되므로 veriNum 조건 추가*/
                if(httpRequest.status == 200 && requestResult.veriNum != null) {
                    /* 인증번호 받아와서 변수 선언 */
                    verifyNumber = requestResult.veriNum;
                    /* 다음 버튼 활성화 */
                    document.getElementById('popupNextModal').disabled=false;
                    /* 이메일 입력창 비활성화 */
                    document.getElementById('inputEmail').disabled=true;
                    /* 인증번호 전송 버튼 비활성화 */
                    document.getElementById('sendVeriNum').disabled=true;
                    console.log(verifyNumber); 

                } else {
                    console.log("AJAX 에러 발생");
                }
                var resultMessage = requestResult.result;
                document.getElementById('checkInputEmailDB').innerText = resultMessage;
                console.log(resultMessage);
            }
        }
        httpRequest.open('POST', '/checkDBEmail?memberEmail=' + inputEmail);
        httpRequest.responseType = "json";
        httpRequest.send();
    });

    /* 인증 번호 일치 여부 확인*/
    document.getElementById('inputVeriNum').addEventListener('keyup', () => {
        console.log(verifyNumber);
        var inputNewNum = document.getElementById('inputVeriNum').value;
        var checkResult = document.getElementById('resultCheckNum');
        var compButton = document.getElementById('veriComplete');
        /* 인증 번호 대조 결과 출력 및 완료 버튼 disabled 변경 */
        if(inputNewNum === verifyNumber) {
            checkResult.innerText = "인증번호가 일치합니다."
            compButton.disabled = false;

        } else {
            checkResult.innerText = "인증번호가 일치하지 않습니다."
            compButton.disabled = true;
        }
    })
    /* 2 ->1 번째 Modal로 이동 시 disabeld 해제 */
    document.getElementById('returnModal').addEventListener('click', () =>{
        /* 다음 버튼 비활성화 */
        document.getElementById('popupNextModal').disabled=true;
        /* 이메일 입력창 활성화 */
        document.getElementById('inputEmail').disabled=false;
        /* 인증번호 발송 버튼 활성화 */
        document.getElementById('sendVeriNum').disabled=false;
        /* 인증메일 발송 결과 메시지 초기화*/
        document.getElementById('resultCheckNum').innerText = "";

    })
    /* 2번째 Modal 에서 변경완료 클릭 */
    document.getElementById('veriComplete').addEventListener('click',() => {
        /* 수정 페이지의 Email 값 변경 */
        document.getElementById('memberEmail').value = inputEmail.value;
        /* Email 하단 안내 메시지 글자 변경 */
        document.getElementById('emailText').innerText="재인증이 완료되었습니다."
        document.getElementById('emailText').style.color="blue";
    })
    

}