package com.example.garenatoolpro;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private final int BG = Color.rgb(13, 15, 22);
    private final int PANEL = Color.rgb(28, 31, 42);
    private final int RED = Color.rgb(255, 55, 72);
    private final int WHITE = Color.WHITE;
    private LinearLayout content;
    private final ArrayList<String> accountDrafts = new ArrayList<>();
    private final SecureRandom secureRandom = new SecureRandom();

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(20, 24, 20, 28);
        content.setBackgroundColor(BG);
        scroll.addView(content);

        TextView mascot = text("🐱👑", 42, WHITE, true);
        mascot.setGravity(Gravity.CENTER);
        content.addView(mascot, full());

        TextView brand = text("GARENA TOOL PRO", 25, WHITE, true);
        brand.setGravity(Gravity.CENTER);
        content.addView(brand, full());

        TextView sub = text("LIÊN QUÂN MOBILE  •  GAMING COMPANION", 11, RED, true);
        sub.setGravity(Gravity.CENTER);
        sub.setPadding(0, 5, 0, 22);
        content.addView(sub, full());

        addSection("SỰ KIỆN CHUNG SỨC",
                "Theo dõi mục tiêu, nhiệm vụ và tiến độ do bạn cập nhật.",
                "MỞ THEO DÕI", "Chung sức");
        addSection("SỰ KIỆN MỚI NHẤT",
                "Ghi chú nhiệm vụ, thời hạn và phần thưởng của sự kiện mới.",
                "MỞ THEO DÕI", "Sự kiện mới");
        addSection("SỰ KIỆN 400 QH",
                "Theo dõi điều kiện tham gia và trạng thái nhận thưởng.",
                "MỞ THEO DÕI", "Sự kiện 400 QH");

        addAccountSection();
        addAccountCreationAssistant();

        TextView disclaimer = text(
            "BẢN COMPANION: Theo dõi thủ công, không tự động farm hoặc thao tác thay người chơi. " +
            "Không thu thập mật khẩu/OTP. Các sự kiện và phần thưởng cần được xác minh trên kênh chính thức.",
            12, Color.LTGRAY, false);
        disclaimer.setPadding(4, 20, 4, 4);
        content.addView(disclaimer, full());

        setContentView(scroll);
    }

    private void addSection(String title, String description, String buttonText, String eventName) {
        LinearLayout card = card();
        TextView heading = text("✦  " + title, 17, WHITE, true);
        card.addView(heading, full());

        TextView detail = text(description, 13, Color.LTGRAY, false);
        detail.setPadding(0, 8, 0, 12);
        card.addView(detail, full());

        Button action = new Button(this);
        action.setText(buttonText);
        action.setTextColor(WHITE);
        action.setAllCaps(false);
        action.setBackground(round(RED, 18));
        card.addView(action, full());
        action.setOnClickListener(v -> showTracker(eventName));
        content.addView(card, cardParams());
    }

    private void showTracker(String eventName) {
        EditText note = new EditText(this);
        note.setSingleLine(false);
        note.setMinLines(2);
        note.setHint("Ghi chú nhiệm vụ / tiến độ của bạn");
        note.setTextColor(WHITE);
        note.setHintTextColor(Color.GRAY);
        note.setBackground(round(PANEL, 16));
        note.setPadding(16, 12, 16, 12);

        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(20, 20, 20, 20);
        box.setBackground(round(PANEL, 20));
        box.addView(text(eventName, 18, WHITE, true), full());
        TextView hint = text("Cập nhật thủ công — bản mẫu chưa kết nối dữ liệu sự kiện trực tiếp.", 12, Color.LTGRAY, false);
        hint.setPadding(0, 8, 0, 12);
        box.addView(hint, full());
        box.addView(note, full());

        new android.app.AlertDialog.Builder(this)
            .setTitle("Theo dõi sự kiện")
            .setView(box)
            .setPositiveButton("Lưu ghi chú", (d, w) ->
                Toast.makeText(this, "Ghi chú chỉ lưu trong phiên demo.", Toast.LENGTH_SHORT).show())
            .setNegativeButton("Đóng", null)
            .show();
    }

    private void addAccountSection() {
        LinearLayout card = card();
        card.addView(text("TÀI KHOẢN THỦ CÔNG", 17, WHITE, true), full());
        TextView description = text(
            "Quản lý tên tài khoản hoặc ghi chú. Không nhập hay lưu mật khẩu và mã OTP trong ứng dụng demo.",
            13, Color.LTGRAY, false);
        description.setPadding(0, 8, 0, 12);
        card.addView(description, full());

        EditText username = new EditText(this);
        username.setSingleLine(true);
        username.setHint("Tên tài khoản / biệt danh");
        username.setTextColor(WHITE);
        username.setHintTextColor(Color.GRAY);
        username.setBackground(round(BG, 14));
        username.setPadding(14, 8, 14, 8);
        card.addView(username, full());

        Button save = new Button(this);
        save.setText("LƯU GHI CHÚ TÀI KHOẢN");
        save.setTextColor(WHITE);
        save.setAllCaps(false);
        save.setBackground(round(RED, 18));
        LinearLayout.LayoutParams p = full();
        p.topMargin = 10;
        card.addView(save, p);
        save.setOnClickListener(v -> {
            String value = username.getText().toString().trim();
            if (value.isEmpty()) {
                username.setError("Nhập tên tài khoản hoặc biệt danh");
            } else {
                Toast.makeText(this, "Đã ghi nhận: " + value + " (demo)", Toast.LENGTH_SHORT).show();
                username.setText("");
            }
        });

        Button official = new Button(this);
        official.setText("MỞ TRANG GARENA CHÍNH THỨC");
        official.setTextColor(WHITE);
        official.setAllCaps(false);
        official.setBackground(round(Color.rgb(65, 67, 78), 18));
        LinearLayout.LayoutParams op = full();
        op.topMargin = 8;
        card.addView(official, op);
        official.setOnClickListener(v -> {
            android.content.Intent i = new android.content.Intent(
                android.content.Intent.ACTION_VIEW,
                android.net.Uri.parse("https://account.garena.com/"));
            startActivity(i);
        });
        content.addView(card, cardParams());
    }

    private void addAccountCreationAssistant() {
        LinearLayout card = card();
        card.addView(text("AUTO CREATE ACCOUNT", 17, WHITE, true), full());

        TextView info = text(
            "Trợ lý chuẩn bị thông tin đăng ký. Mỗi tài khoản phải được đăng ký và xác minh thủ công trên trang Garena chính thức.",
            13, Color.LTGRAY, false);
        info.setPadding(0, 8, 0, 12);
        card.addView(info, full());

        EditText quantity = new EditText(this);
        quantity.setSingleLine(true);
        quantity.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        quantity.setHint("Số bộ thông tin cần tạo (1–5)");
        quantity.setTextColor(WHITE);
        quantity.setHintTextColor(Color.GRAY);
        quantity.setBackground(round(BG, 14));
        quantity.setPadding(14, 8, 14, 8);
        card.addView(quantity, full());

        Button generate = new Button(this);
        generate.setText("TẠO THÔNG TIN ĐĂNG KÝ");
        generate.setTextColor(WHITE);
        generate.setAllCaps(false);
        generate.setBackground(round(RED, 18));
        LinearLayout.LayoutParams gp = full();
        gp.topMargin = 10;
        card.addView(generate, gp);

        TextView output = text("", 13, WHITE, false);
        output.setPadding(0, 12, 0, 8);
        card.addView(output, full());

        generate.setOnClickListener(v -> {
            String raw = quantity.getText().toString().trim();
            int n;
            try { n = Integer.parseInt(raw); }
            catch (Exception e) { n = 0; }
            if (n < 1 || n > 5) {
                quantity.setError("Chọn từ 1 đến 5");
                return;
            }
            StringBuilder result = new StringBuilder();
            accountDrafts.clear();
            for (int i = 0; i < n; i++) {
                String username = "LQ" + randomChars(8);
                String password = randomPassword(16);
                String entry = "Tên gợi ý: " + username + "\nMật khẩu đề xuất: " + password;
                accountDrafts.add(entry);
                result.append("BỘ ").append(i + 1).append("\n")
                    .append(entry).append("\n\n");
            }
            result.append("Lưu ý: Đây chỉ là thông tin gợi ý. Hãy kiểm tra quy tắc tên/mật khẩu trên Garena. Không dùng lại mật khẩu và không chia sẻ mã OTP.");
            output.setText(result.toString());

            Button copy = new Button(this);
            copy.setText("SAO CHÉP THÔNG TIN");
            copy.setTextColor(WHITE);
            copy.setAllCaps(false);
            copy.setBackground(round(Color.rgb(65, 67, 78), 18));
            card.addView(copy, full());
            copy.setOnClickListener(view -> {
                android.content.ClipboardManager clipboard =
                    (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(android.content.ClipData.newPlainText("Account drafts", result.toString()));
                Toast.makeText(this, "Đã sao chép thông tin gợi ý", Toast.LENGTH_SHORT).show();
            });

            Button signup = new Button(this);
            signup.setText("MỞ TRANG ĐĂNG KÝ GARENA");
            signup.setTextColor(WHITE);
            signup.setAllCaps(false);
            signup.setBackground(round(RED, 18));
            LinearLayout.LayoutParams sp = full();
            sp.topMargin = 8;
            card.addView(signup, sp);
            signup.setOnClickListener(view -> {
                android.content.Intent intent = new android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse("https://account.garena.com/"));
                startActivity(intent);
            });
        });
        content.addView(card, cardParams());
    }

    private String randomChars(int length) {
        final String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) s.append(chars.charAt(secureRandom.nextInt(chars.length())));
        return s.toString();
    }

    private String randomPassword(int length) {
        final String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) s.append(chars.charAt(secureRandom.nextInt(chars.length())));
        return s.toString();
    }

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(17, 17, 17, 17);
        c.setBackground(round(PANEL, 20));
        return c;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(null, Typeface.BOLD);
        return t;
    }

    private GradientDrawable round(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private LinearLayout.LayoutParams full() {
        return new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private LinearLayout.LayoutParams cardParams() {
        LinearLayout.LayoutParams p = full();
        p.bottomMargin = 14;
        return p;
    }
}
