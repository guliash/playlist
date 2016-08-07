package com.github.guliash.playlist.structures;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class. Contains predefined {@link Singer} objects
 */
public class Constants {

    public static final int SINGER_FIRST_ID = 92178;
    public static final int SINGER_SECOND_ID = 36784;

    private static final String[] singerJSONS = {
            "{\"albums\":17,\"cover\":{\"big\":\"http://avatars" +
                    ".yandex.net/get-music-content/7da872d1.p.92178/1000x1000\",\"small\":\"http://avatars" +
                    ".yandex.net/get-music-content/7da872d1.p.92178/300x300\"},\"description\":\"американская" +
                    " рок-группа из Лос-Анджелеса, штат Калифорния, исполняющая альтернативный рок. Основана " +
                    "в 1998 году братьями Джаредом и Шенноном Лето. Название группы было взято участниками из" +
                    " статьи бывшего гарвардского профессора, где шла речь о технологическом прогрессе, в" +
                    " результате которого человечество в буквальном смысле слова окажется вскоре «в тридцати " +
                    "секундах от Марса».\",\"genres\":[\"alternative\"],\"id\":92178,\"link\":\"http://www." +
                    "thirtysecondstomars.com/\",\"name\":\"30 Seconds To Mars\",\"tracks\":72}",
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
     *
     * @return
     */
    public static List<Singer> getSingers() {
        return singers;
    }

    /**
     * Gets a predefined user with the given id
     *
     * @param id the id
     * @return the singer with the given id
     */
    public static Singer getSinger(int id) {
        for (Singer singer : singers) {
            if (singer.id == id) {
                return singer;
            }
        }
        throw new AssertionError();
    }
}