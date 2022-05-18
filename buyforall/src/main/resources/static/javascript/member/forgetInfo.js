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
    //아이디, 이메일 입력을 통한 비밀번호를 재설정하는 ajax
    document.getElementById("EmailIdForSearchPwd").addEventListener('click',() => {
        var idForPwd = document.getElementById("inputIdForPwd").value;
        var emailForPwd = document.getElementById("inputEmailForPwd").value;
        if(idForPwd == "" ||  emailForPwd == "") {
            document.getElementById('resultText2').innerText = "모든 항목을 기입해야 합니다."
        } else {
            httpRequest = new XMLHttpRequest();
            httpRequest.onreadystatechange = () => {
                if(httpRequest.readyState === XMLHttpRequest.DONE) {
                    if(httpRequest.status == 200) {
                        var requestResult2 = httpRequest.response;
                        var resultMessage2 = requestResult2.result2;

                        document.getElementById('resultText2').innerText = resultMessage2;
                    }
                }
            }
            httpRequest.open('POST', '/searchPwd?memberId=' + idForPwd + '&memberEmail=' + emailForPwd);
            httpRequest.responseType = "json";
            httpRequest.send();   
        }

    })
}