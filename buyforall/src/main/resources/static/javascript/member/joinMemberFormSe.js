window.onload = function () {

    /* 회원 아이디 중복 체크 Ajax */
    document.getElementById('memberId').addEventListener('keyup',() => {
        var inputedId = document.getElementById('memberId').value;
        var requestJson = new Object();
        var resutIdResult = "";
        requestJson.name = inputedId;
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = () => {
            if(httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status == 200) { 
                    /* View로 돌아온 map 변수 선언 */
                    var requestResult = httpRequest.response;

                    var resultMessage = requestResult.result;
                    var buttonActive = requestResult.nextButton;
                    /* 체크 결과 View에 출력 */
                    document.getElementById('checkIDResult').innerText = resultMessage;
                    /* 결과 값에 따라 '다음' 버튼 활성/비활성화 */
                    if (buttonActive == "abled") {
                        document.getElementById('nextButton').disabled=false;
                    } else if (buttonActive == "disabled") {
                        document.getElementById('nextButton').disabled=true;
                    }
                } else {
                    console.log("Ajax 에러")
                }
            }
        };
        httpRequest.open('POST','checkDBId?memberId=' + inputedId);
        httpRequest.responseType = "json";
        httpRequest.send();
    })
    
}