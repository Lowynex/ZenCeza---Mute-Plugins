# ZenCeza (`ZentoriaMute`)

Paper 1.21+ sunucuları için Java 21 ile geliştirilmiş, süreli sohbet susturması (`mute`) ve moderasyon duyuruları sunan hafif bir Minecraft eklentisidir.

## Özellikler

* **Görsel ve İşitsel Uyarılar:** Ceza uygulanan oyuncuya `Title & Subtitle` gösterir ve `ENTITY_ENDER_DRAGON_GROWL` ses efekti çalar.
* **Sunucu Geneli Duyuru:** Uygulanan her ceza sohbet üzerinden düzenli bir kart olarak tüm sunucuya duyurulur; ceza kaldırıldığında bilgilendirme ve ses efekti (`ENTITY_EXPERIENCE_ORB_PICKUP`) tetiklenir.
* **Sohbet Engelleme Sistemi:** Susturulmuş oyuncuların mesajları engellenir, kalan süre saniye cinsinden gösterilir ve `ENTITY_VILLAGER_NO` sesi çalınır.
* **Süre Desteği:** `s` (saniye), `d` (dakika) ve `g` (gün) birimlerini destekler.
* **Kalıcı Kayıt:** Aktif cezalar `config.yml` dosyasına kaydedilir ve sunucu açılışında süresi dolmamış cezalar belleğe yüklenir.

## Komutlar

* `/ceza <oyuncu> <süre (g/d/s)> <sebep>` — Çevrimiçi oyuncuyu belirtilen süre ve sebeple susturur.
* `/unceza <oyuncu>` — Oyuncunun aktif susturmasını kaldırır ve kaydını siler.

## Yetkiler

| Yetki | Açıklama |
| :--- | :--- |
| `zentoriamute.admin` | `/ceza` ve `/unceza` komutlarına erişim sağlar. |

## Kurulum ve Derleme

1. `ZenCeza.jar` dosyasını sunucunuzun `plugins` klasörüne bırakın ve sunucuyu başlatın.
2. Ayarlar ve kayıtlı cezalar `plugins/ZentoriaMute/config.yml` altında tutulur.
3. Kaynak koddan derlemek için Maven kullanabilirsiniz:

```bash
mvn clean package
