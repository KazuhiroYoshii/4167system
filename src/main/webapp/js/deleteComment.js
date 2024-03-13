/**
 * 削除実行前に確認ダイアログを表示する
 */
function checkDelete(){
     var result = confirm('削除しますか？');
     if(result == true){
         return true;
     }else{
         return false;
     }
}