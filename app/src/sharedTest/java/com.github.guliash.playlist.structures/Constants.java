package com.github.guliash.playlist.structures;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class. Contains predefined {@link Singer} objects
 */
public class Constants {

    public static final int SINGER_ID = 36784;

    private static final String[] singerJSONS = {"{\"albums\":45,\"cover\":{\"big\":\"" +
            "http://avatars.yandex.net/get-music-content/a0e5b2b6.p.36800/1000x1000\"," +
            "\"small\":\"http://avatars.yandex.net/get-music-content/a0e5b2b6.p.36800/300x300\"}," +
            "\"description\":\"американская альтернативная рок-группа, основанная в 1996 году под " +
            "названием Xero. Существуя с 2000 года под названием Linkin Park, группа два раза " +
            "удостоилась награды «Грэмми». Группа обрела успех благодаря дебютному альбому 2000 " +
            "года под названием Hybrid Theory, проданному тиражом в 27 миллионов экземпляров. " +
            "Следующий студийный альбом, Meteora, повторил, хоть и не превзошёл успех предыдущего, " +
            "лидируя в 2003 году в чарте Billboard 200. В общей сложности альбомы группы разошлись " +
            "общим тиражом около 73 миллионов экземпляров.\",\"genres\":[\"alternative\"]," +
            "\"id\":36800,\"link\":\"http://www.linkinpark.com/main\",\"name\":\"Linkin Park\"," +
            "\"tracks\":309}",
            "{\"albums\":28,\"cover\":{\"big\":\"http://avatars.yandex.net/get-" +
                    "music-content/bf50a239.p.36784/1000x1000\",\"small\":\"http://avatars.yandex.net/get-" +
                    "music-content/bf50a239.p.36784/300x300\"},\"description\":\"канадская рок-группа, " +
                    "играет в стиле альтернативный рок, образована в 1995 году в городе Ханна. Группа " +
                    "состоит из гитариста и вокалиста Чеда Крюгера, гитариста, клавишника и бэк-вокалиста " +
                    "Райана Пике, басиста Майка Крюгера, и барабанщика Дэниеля Адаира. Название группы " +
                    "образовано от Here’s your nickel back. - «Вот ваша сдача». Или дословно: " +
                    "«Вот ваши пять центов сдачи». Никель - американское название пятицентовой монеты. " +
                    "Группа попала в список лучших артистов первого десятилетия XXI века, по мнению Billboard, " +
                    "где заняла первое место среди рок-групп.\",\"genres\":[\"rock\"],\"id\":36784,\"link\"" +
                    ":\"http://nickelback.com/\",\"name\":\"Nickelback\",\"tracks\":116}"};

    private static List<Singer> singers;

    static {
        Gson gson = new Gson();
        singers = new ArrayList<>();
        for (String json : singerJSONS) {
            singers.add(gson.fromJson(json, Singer.class));
        }
    }

    /**
     * Returns predefined list of singers
     * @return
     */
    public static List<Singer> getSingers() {
        return singers;
    }

    /**
     * Returns the singer with {@link Constants#SINGER_ID}
     *
     * @return the singer with {@link Constants#SINGER_ID}
     */
    public static Singer getTestSinger() {
        return singers.get(1);
    }
}