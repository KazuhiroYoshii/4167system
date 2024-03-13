/**
 * 削除実行前に確認ダイアログを表示する
 * 
 * 【引用元】Alterbo Inc. JavaScriptでダイアログボックスを表示するには
 * https://alterbo.jp/blog/riri-210405/
 */
function checkDelete(){
     var result = confirm('削除しますか？');
     if(result == true){
         return true;
     }else{
         return false;
     }
}