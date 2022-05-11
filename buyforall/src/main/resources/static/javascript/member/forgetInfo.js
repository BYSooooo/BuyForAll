window.onload=function() {
    //이메일 입력을 통해 아이디를 찾는 ajax
    document.getElementById("EmailForSearchId").addEventListener('click', () => {
        var inputEmail = document.getElementById("inputEmailForEmail").value;
        if (inputEmail == "") {
            document.getElementById('resultText').innerText = "가입 시 사용하신 이메일을 입력하시기 바랍니다."
        } else {
            httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = () => {
                if(httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status == 200) {
                        //ajax 결과값 변수 선언
                        var requestResult = httpRequest.response;
                        var resultMessage = requestResult.result;
                        console.log(resultMessage);
                        document.getElementById('resultText').innerText = resultMessage;
                    }
                }        
            }
            httpRequest.open('POST', '/searchIdForEmail?memberEmail=' + inputEmail);
            httpRequest.responseType = "json";
            httpRequest.send();
        }
        
    })
}