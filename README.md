[Trelloボード](https://trello.com/b/pU3ogVEu/jinrocraft)
~~我らがリアドさんのメモ~~

・人狼ゲーム
村人陣営：人狼、妖狐をすべて倒す
人狼陣営：人狼の数が村人の数を上回ったとき
陽子陣営：妖狐が最後まで生き残る

遊ぶフィールド：サバイバル
村人陣営はサバイバルで資材を集めて、人狼に対抗したり、
人狼陣営は資材を奪ったり邪魔したり、
妖狐は最後までヘイトが集まらないようにする感じのゲームになると思います

詳細なルール
一度死ぬとプレイヤーのは墓が生成され、スペクターモードになります
死亡しても「プレイヤー名が襲撃されました」と表示されるだけで誰に殺されたかなどはわからないです

・役職
市民、霊能、占い師、狩人、人狼、狂人、妖狐

詳細
　市民　能力はありません
　霊能　プレイヤーが死亡するとプレイヤーの墓が生成されます、霊能者はハサミを手に持って墓を右クリックすると、死亡したプレイヤーが人狼であるかどうかを判定できます
　占い師　ハサミを手に持ってプレイヤーを右クリックすると経験値を10消費して人狼であるかどうかを判定できます
　狩人　一定時間ごとに矢を入手します
　人狼　夜の間、攻撃によるダメージが2増加します
　狂人　能力はありません
　妖狐　人狼からダメージを受けません

実装されていないもの
・役職の自動振り分け
　op権限があるゲームマスターが
 /role set UserName 役職名 を実行する必要があります
・投票システム
　投票ができないので、既存のプラグインなどを利用してください
・勝利判定
　勝利判定機能がないので、ゲームマスターが試合終了を伝える必要があります

今後の予定（？）
・役職ごとのショップ機能
　例）インベントリの鉄を10個消費して特別なアイテムと交換するなど
・役職を増やす