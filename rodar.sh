PROJECT_DIR="$(pwd)"

JAVAFX_LIB="/home/mendes/Documentos/Java/javafx-sdk-21.0.2/lib"
SRC_DIR="$PROJECT_DIR/SUN_PDV/src"
BIN_DIR="$PROJECT_DIR/SUN_PDV/bin"

echo "[1/5] Compilando..."
mkdir -p "$BIN_DIR"
javac \
  --module-path "$JAVAFX_LIB" \
  --add-modules javafx.controls,javafx.fxml \
  -d "$BIN_DIR" \
  "$SRC_DIR/com/br/TelaLogin.java"

echo "[2/5] Copiando CSS..."
mkdir -p "$BIN_DIR/com/Style"
cp "$SRC_DIR/com/Style/LoginGeral.css" "$BIN_DIR/com/Style/"

echo "[3/5] Conferindo Código..."
echo "[4/5] Conectando ao Sistema..."

echo "[5/5] Executando..."
/usr/lib/jvm/java-17-openjdk/bin/java \
  --module-path "$JAVAFX_LIB" \
  --add-modules javafx.controls,javafx.fxml \
  --class-path "$BIN_DIR:$JAVAFX_LIB/*" \
  com.br.TelaLogin
