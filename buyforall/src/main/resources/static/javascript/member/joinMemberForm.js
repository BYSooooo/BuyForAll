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
                if (httpRequest.status == 200) {
                    var result = httpRequest.response;
                    
                } else {
                    console.log("AJAX 에러 발생")
                }
            }
        };
        httpRequest.open('POST', '/checkDBEmail?memberEmail=' + memberEmail);
        httpRequest.responseType = "json";
        httpRequest.send();
    })
}