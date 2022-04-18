$(document).ready(function() {

    // 가입 과정 중 Email 중복 검사
    $('checkEmail').click(function() {
        const memberEmail = $('#memberEmail').val();
        checkEmailAjax('/checkDBEmail', memberEmail);
    })

    // 이메일 체크 Ajax
    const checkEmailAjax = function(url, memberEmail) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                method: 'POST',
                data: {
                    memberEmail: memberEmail
                },
                success: function (data) {
                    resolve(data);
                },
                error: function(e) {
                    reject(e);
                }
            })
        })
    }

})