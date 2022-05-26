window.onload = function () {
    
    /* 발행된 인증 번호를 담는 변수 */
    var verifyNumber = "";
    /* input을 통해 사용자가 입력한 인증번호 변수 */
    var inputVeriNum = document.getElementById('inputNumber');
    /* 인증 번호 일치 여부를 표시하는 Text 변수 */
    var checkNumResult = document.getElementById('checkVeriNum');

    // 가입 과정 중 Email 중복 검사
    document.getElementById('checkEmail').addEventListener('click',() => {
        var memberEmail = document.getElementById("memberEmail").value;
        var requestJson = new Object();
        requestJson.name = memberEmail;
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                var requestResult = httpRequest.response;
                if (httpRequest.status == 200) {
                    /* 인증번호 받아와서 변수 선언 */
                    verifyNumber = requestResult.veriNum;
                    /* 인증번호 입력 input 활성화 */
                    inputVeriNum.disabled =  false;
                    /* 버튼 text 변경 */
                    document.getElementById('checkEmail').innerText = "인증번호 재발송";
                    /* 진행 상태 표시 요소 display 속성 변경 */
                    document.getElementById('taskOne').style.display = "none";
                    document.getElementById('taskTwo').style.display = "inline";

                    console.log(verifyNumber);
                } else {
                    console.log("AJAX 에러 발생");
                }
                var resultMessage = requestResult.result;
                document.getElementById('checkDBEmail').innerText = resultMessage;
                console.log(resultMessage);
            }
        };
        httpRequest.open('POST', '/checkDBEmail?memberEmail=' + memberEmail);
        httpRequest.responseType = "json";
        httpRequest.send();
    })

    /* 가입 과정 중 인증번호 일치 여부에 따른 버튼 활성/비활성 */
    inputVeriNum.addEventListener('keyup',() => {
        var verifyNum2 = verifyNumber;
        if(verifyNum2 === inputVeriNum.value) {
            document.getElementById('nextButton').disabled=false;
            checkNumResult.innerText = "인증번호가 일치합니다."
        } else {
            document.getElementById('nextButton').disabled=true;
            checkNumResult.innerText = "인증번호가 일치하지 않습니다."
        }
    })

}