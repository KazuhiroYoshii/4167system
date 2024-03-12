/**
 * 今日の日付を取得し、最小値に格納する
 */
window.onload = function() {
	// 日付情報を取得する
	var now = new Date();
	// 年を取得する
	var YYYY = now.getFullYear();
	// 月を取得する（一桁月の時は前に「0」を入れる　例)01）
	var MM = ("0" + (now.getMonth() + 1)).slice(-2);
	// 日を取得する（一桁日の時は前に「0」を入れる　例)01）
	var DD = ("0" + now.getDate()).slice(-2);
	// 入力形式
	var today = YYYY + "-" + MM + "-" + DD;

	// 期限の初期値を今日に設定する
	document.getElementById("today").setAttribute("value", today);
	// 期限の最小値を今日に設定する
	document.getElementById("today").setAttribute("min", today);
}
