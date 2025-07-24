package com.Auction.Auction_website.Csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CsvUtil {
    public static <T> List<T> read(Class<T> clazz, String path) throws IOException {
        try (Reader reader = new FileReader(path)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }
//    public static Map<String, BattingStatsDTO> aggregateBatting(Class cls,String path) throws Exception {
//        List<BattingCsv> list = read(cls,path);
//        Map<String, BattingStatsDTO> map = new HashMap<>();
//
//        for (BattingCsv record : list) {
//            String name = record.getName();
//            BattingStatsDTO dto = map.getOrDefault(name, new BattingStatsDTO());
//
//            dto.setName(name);
//            dto.setFifties(dto.getFifties() + record.getFifties());
//            dto.setCenturies(dto.getCenturies() + record.getCenturies());
//            dto.setRuns(dto.getRuns() + record.getRuns());
//            dto.setMatches(Math.max(dto.getMatches(), record.getMatchesBatted()));
//
//            map.put(name, dto);
//        }
//
//        return map;
//    }
//
//    public static Map<String, BowlingStatsDTO> aggregateBowling(Class cls,String path) throws Exception {
//        List<BowlingCsv> list = read(cls,path);
//        Map<String, BowlingStats> map = new HashMap<>();
//
//        for (BowlingCSV record : list) {
//            String name = record.getName();
//            BowlingStatsDTO dto = map.getOrDefault(name, new BowlingStatsDTO());
//
//            dto.setName(name);
//            dto.setWickets(dto.getWickets() + record.getWickets());
//            dto.setBestSpell(record.getBestSpell());
//            dto.setMatches(Math.max(dto.getMatches(), record.getMatchesBowled()));
//            dto.setBowlingStyle(record.getBowlingStyle());
//            dto.setThreeWicketHaul(dto.getThreeWicketHaul() + record.getThreeWicketHaul());
//            dto.setFiveWicketHaul(dto.getFiveWicketHaul() + record.getFiveWicketHaul());
//
//            map.put(name, dto);
//        }
//
//        return map;
//    }
//
//    public static Map<String, AllrounderStatsDTO> aggregateAllrounder(Class cls,String path) throws Exception {
//        List<AllrounderCsv> list = read(cls,path);
//        Map<String, AllrounderStatsDTO> map = new HashMap<>();
//
//        for (AllrounderCsv record : list) {
//            String name = record.getName();
//            AllrounderStatsDTO dto = map.getOrDefault(name, new AllrounderStatsDTO());
//
//            dto.setName(name);
//            dto.setFifties(dto.getFifties() + record.getFifties());
//            dto.setCenturies(dto.getCenturies() + record.getCenturies());
//            dto.setRuns(dto.getRuns() + record.getRuns());
//            dto.setWickets(dto.getWickets() + record.getWickets());
//            dto.setMatches(Math.max(dto.getMatches(), Math.max(record.getMatchesBatted(), record.getMatchesBowled())));
//            dto.setBattingStyle(record.getBattingStyle());
//            dto.setBowlingStyle(record.getBowlingStyle());
//            dto.setThreeWicketHaul(dto.getThreeWicketHaul() + record.getThreeWicketHaul());
//            dto.setFiveWicketHaul(dto.getFiveWicketHaul() + record.getFiveWicketHaul());
//
//            map.put(name, dto);
//        }
//
//        return map;
//    }
}
