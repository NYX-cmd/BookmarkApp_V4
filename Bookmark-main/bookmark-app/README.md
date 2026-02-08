# ブックマーク管理アプリ

初心者向けのシンプルなブックマーク管理アプリケーションです。

## 機能

✅ **ブックマーク保存** - タイトル、URL、説明を保存
✅ **お気に入り機能** - ブックマークをお気に入りに追加
✅ **並び替え機能** - 新しい順、古い順、タイトル順、お気に入りのみ
✅ **ログイン機能** - ユーザー認証とセッション管理

## 技術スタック

- **Java**: 17
- **Spring Boot**: 4.0.0
- **Maven**: プロジェクト管理
- **H2 Database**: インメモリデータベース
- **Spring Security**: 認証・認可
- **Thymeleaf**: テンプレートエンジン

## プロジェクト構成

```
bookmark-app/
├── src/
│   └── main/
│       ├── java/com/example/bookmarkapp/
│       │   ├── BookmarkApplication.java      # メインクラス
│       │   ├── config/
│       │   │   └── SecurityConfig.java       # セキュリティ設定
│       │   ├── controller/
│       │   │   ├── AuthController.java       # 認証コントローラー
│       │   │   └── BookmarkController.java   # ブックマークコントローラー
│       │   ├── model/
│       │   │   ├── User.java                 # ユーザーエンティティ
│       │   │   └── Bookmark.java             # ブックマークエンティティ
│       │   ├── repository/
│       │   │   ├── UserRepository.java       # ユーザーリポジトリ
│       │   │   └── BookmarkRepository.java   # ブックマークリポジトリ
│       │   └── service/
│       │       ├── UserService.java          # ユーザーサービス
│       │       ├── BookmarkService.java      # ブックマークサービス
│       │       └── CustomUserDetailsService.java
│       └── resources/
│           ├── application.properties        # アプリケーション設定
│           ├── static/css/
│           │   └── style.css                 # スタイルシート
│           └── templates/
│               ├── login.html                # ログインページ
│               ├── register.html             # 登録ページ
│               ├── bookmarks.html            # ブックマーク一覧
│               ├── bookmark-form.html        # 新規作成フォーム
│               └── bookmark-edit.html        # 編集フォーム
└── pom.xml                                    # Maven設定
```

## セットアップと実行

### 必要な環境
- Java 17以上
- Maven 3.6以上

### 実行方法

1. **プロジェクトをビルド**
```bash
mvn clean install
```

2. **アプリケーションを起動**
```bash
mvn spring-boot:run
```

3. **ブラウザでアクセス**
```
http://localhost:8080
```

## 使い方

### 1. ユーザー登録
- 新規登録ページでユーザー名とパスワードを入力
- パスワードは自動的にハッシュ化されて保存されます

### 2. ログイン
- 登録したユーザー名とパスワードでログイン

### 3. ブックマーク追加
- 「+ 新規ブックマーク」ボタンをクリック
- タイトル、URL、説明(オプション)を入力して保存

### 4. お気に入り機能
- 各ブックマークの星マーク(★)をクリックでお気に入り切り替え
- 「お気に入りのみ」フィルターで表示可能

### 5. 並び替え
- 新しい順: 最近追加したブックマークから表示
- 古い順: 古いブックマークから表示
- タイトル順: タイトルのアルファベット順
- お気に入りのみ: お気に入りのブックマークのみ表示

### 6. 編集・削除
- 各ブックマークの「編集」ボタンで内容を変更
- 「削除」ボタンで削除(確認ダイアログあり)

## データベース

H2インメモリデータベースを使用しています。

### H2コンソールへのアクセス
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:bookmarkdb
ユーザー名: sa
パスワード: (空白)
```

## セッター・ゲッターについて

すべてのエンティティクラス(`User.java`, `Bookmark.java`)には、
適切なセッター・ゲッターメソッドが実装されています。

```java
// 例: Bookmark.java
public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}
```

## セキュリティ

- パスワードはBCryptでハッシュ化
- Spring Securityによる認証・認可
- ログインセッション管理
- CSRF保護

## カスタマイズ

### ポート番号の変更
`application.properties`で変更できます:
```properties
server.port=8080
```

### データベースの永続化
H2をファイルベースに変更する場合:
```properties
spring.datasource.url=jdbc:h2:file:./data/bookmarkdb
```

## トラブルシューティング

### ポート8080が使用中の場合
`application.properties`でポート番号を変更してください。

### ビルドエラーが発生する場合
```bash
mvn clean
mvn install -U
```

## ライセンス

このプロジェクトは学習目的で作成されています。
