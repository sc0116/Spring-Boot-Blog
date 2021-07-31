let index = {
    init: function () {
        $("#btn-save").on("click", () => {  //this를 바인딩하기 위해서 () => {}사용, function() X
            this.save();
        });
        $("#btn-login").on("click", () => {
           this.login();
        });
    },
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //ajax호출 시 default가 비동기 호출
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
            type: "post",
            url: "/blog/api/user",
            data: JSON.stringify(data),  //http body데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType: "json"    //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => js 오브젝트로 변경해줌
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
            location.href="/blog";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    login: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        $.ajax({
            type: "post",
            url: "/blog/api/user/login",
            data: JSON.stringify(data),  //http body데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType: "json"    //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => js 오브젝트로 변경해줌
        }).done(function (res) {
            alert("로그인이 완료되었습니다.");
            location.href="/blog";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }
}

index.init();