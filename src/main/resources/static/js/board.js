let index = {
    init: function () {
        $("#btn-save").on("click", () => {  //this를 바인딩하기 위해서 () => {}사용, function() X
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "post",
            url: "/api/board",
            data: JSON.stringify(data),  //http body데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType: "json"    //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => js 오브젝트로 변경해줌
        }).done(function (res) {
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    deleteById: function () {
        let id = $("#id").text();

        $.ajax({
            type: "delete",
            url: "/api/board/"+id,
            dataType: "json"    //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => js 오브젝트로 변경해줌
        }).done(function (res) {
            alert("삭제가 완료되었습니다.");
            location.href="/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    update: function () {
        let id = $("#id").val()
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "put",
            url: "/api/board/" + id,
            data: JSON.stringify(data),  //http body데이터
            contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType: "json"    //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => js 오브젝트로 변경해줌
        }).done(function (res) {
            alert("글수정이 완료되었습니다.");
            location.href="/";
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }
}

index.init();