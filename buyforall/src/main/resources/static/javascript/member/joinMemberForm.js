window.onload = function () {
    // 가입 과정 중 Email 중복 검사
    document.getElementById('checkEmail').addEventListener('click',() => {
        var memberEmail = document.getElementById("memberEmail").value;
        console.log("javascript : " + memberEmail);
        var requestJson = new Object();
        requestJson.name = memberEmail;
        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                var requestResult = httpRequest.response;
                if (httpRequest.status == 200) {
                    var verifyNumber = requestResult.veriNum;
                    console.log(verifyNumber);
                } else {
                    console.log("AJAX 에러 발생");
                    
                }
                var resultMessage = requestResult.result;
                console.log(resultMessage);
            }
        };
        httpRequest.open('POST', '/checkDBEmail?memberEmail=' + memberEmail);
        httpRequest.responseType = "json";
        httpRequest.send();
    })
}