window.onload = function () {
    var BtnToSeStatus = document.getElementById('toJoinSe');
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
                    BtnToSeStatus.disabled =  false;
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
    
    /* sectionOne 에서 '다음' 클릭 시 sectionTwo로 스크롤 */
    document.getElementById('toJoinSe').addEventListener('click', () => {
        document.getElementById('sectionTwo').scrollIntoView({behavior:'smooth',inline:'start'})

    })
    /* sectionTwo에서 '이전' 클릭 시 sectionOne로 스크롤 + '다음' 버튼 비 활성화 */
    document.getElementById('beforeButton').addEventListener('click',() => {
        document.getElementById('sectionOne').scrollIntoView({behavior:'smooth', inline:'end'})
        BtnToSeStatus.disabled=true;
    })
}