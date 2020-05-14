$(document).ready(function(){
	// 정규식
	$('#idck').click(function(){
		// 1. 입력태그에 입력된 아이디 가져오고
		var sid = $('#id').val();
		alert('중복확인 정규식안만듦');
		if(sid) {
			$.ajax({
				url: '../id.check',
				type: 'POST',
				dataType: 'json',
				data: {
					'id': sid
				},
				success: function(data) {
					var result = data.result;
					if(result == 'ok'){
						// 아이디 사용 가능한 경우
						$('#idmsg').text('### 사용 가능한 아이디입니다 ###');
						$('#idmsg').css('color', 'blue');
						$('#idmsg').css('display', '');
						
					} else {
						// 아이디가 사용 불가능한 경우
						$('#idmsg').text('### 사용할 수 없는 아이디입니다 ###');
						$('#idmsg').css('color', 'red');
						$('#idmsg').css('display', '');
						$('#id').val('');
						$('#id').focus();
						
						$('#idCont').css('display', '');
						$('#getId').html(data.id);
						$('#getName').html(data.name);
						$('#getTel').html(data.tel);
						$('#getMail').html(data.mail);
					}
				},
				error: function() {
					alert('### 통신 실패 ###');
				}
			});
		} else {
			$('#id').focus();
			return;
		}
		
	});
	
	// 비번일치
	$('#pwck').keyup(function(){
		var pw = $('#pw').val();
		var pwck = $('#pwck').val();
		if(pw == pwck){
			$('#pwckshow').html('일치');
			$('#pwckshow').css('color', 'blue');
		} else{
			$('#pwckshow').html('다름');			
			$('#pwckshow').css('color', 'red');
		}
	});
	
	// 프사 미리보기
	$('#profile').change(function(e){
		var img = URL.createObjectURL(e.target.files[0]);
//		document.getElementById('img').setAttribute('src', img);
		$('#img').attr('src', img);
	});
	
	// 성별 선택해서 아바타 선택창 띄우기
	$('#M').click(function(){
//		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar1.png"><img class="w3-padding imgsize" src="../img/img_avatar2.png"><img class="w3-padding imgsize" src="../img/img_avatar3.png">'
		$('#genbox').html('<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label><div class="w3-col m9 rel"><input type="radio" id="avt1" name="avtgen" class="w3-radio"><label for="avt1"><img class=" w3-button imgsize" src="../img/img_avatar1.png"></label><input type="radio" id="avt2" name="avtgen" class="w3-radio"><label for="avt2"><img class=" w3-button imgsize" src="../img/img_avatar2.png"></label><input type="radio" id="avt3" name="avtgen" class="w3-radio"><label for="avt3"><img class=" w3-button imgsize" src="../img/img_avatar3.png"></label></div>');
	});
	$('#W').click(function(){
//		document.getElementById('genbox').innerHTML = '<img class="w3-padding imgsize" src="../img/img_avatar4.png"><img class="w3-padding imgsize" src="../img/img_avatar5.png"><img class="w3-padding imgsize" src="../img/img_avatar6.png">'
		$('#genbox').html('<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label><div class="w3-col m9 rel"><input type="radio" id="avt4" name="avtgen" class="w3-radio"><label for="avt4"><img class=" w3-button imgsize" src="../img/img_avatar4.png"></label><input type="radio" id="avt5" name="avtgen" class="w3-radio"><label for="avt5"><img class=" w3-button imgsize" src="../img/img_avatar5.png"></label><input type="radio" id="avt6" name="avtgen" class="w3-radio"><label for="avt6"><img class=" w3-button imgsize" src="../img/img_avatar6.png"></label></div>');
	});
	
	// 년월일
	for(var i=1900; i<=2020; i++){
		$('#year').append('<option>' + i + '</option>');	
	}
	
	for(var i=1; i<=12; i++){
		$('#month').append('<option>' + i + '</option>');	
	}
	
	for(var i=1; i<=31; i++){
		$('#day').append('<option>' + i + '</option>');	
	}
	
	// 가입/취소얼럿
	$('#sign').click(function(){
		alert('가입완료 서밋 안만듦');
		$('#frm').submit();
	});
	$('#cancel').click(function(){
		alert('홈으로돌아감 홈링크안만듦');
		
	});
	
});