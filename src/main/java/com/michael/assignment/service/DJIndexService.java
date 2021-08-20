package com.michael.assignment.service;

import com.michael.assignment.entity.DJIndex;
import com.michael.assignment.repository.DJIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author michaelwang on 2021-08-18
 */
@Service
public class DJIndexService {

    private DJIndexRepository djIndexRepository;

    @Autowired
    public void setDjIndexRepository(DJIndexRepository djIndexRepository) {
        this.djIndexRepository = djIndexRepository;
    }

    public List<DJIndex> getIndicesByStock(String stock){
        return djIndexRepository.findDJIndicesByStock(stock);
    }

    public void saveFile(MultipartFile file) {
    }

    public DJIndex convertToDJIndex(String l) {
        String [] ltr = l.split(",");
        DJIndex djIndex = new DJIndex();
        djIndex.setQuarter(Integer.valueOf(ltr[0]));
        djIndex.setStock(ltr[1]);
        djIndex.setDate(ltr[2]);
        djIndex.setOpen(ltr[3]);
        djIndex.setHigh(ltr[4]);
        djIndex.setLow(ltr[5]);
        djIndex.setClose(ltr[6]);
        djIndex.setVolume(Integer.valueOf(ltr[7]));
        djIndex.setPercent_change_price(Double.valueOf(ltr[8].length()==0?"0":ltr[8]));
        djIndex.setPercent_change_volume_over_last_wk(Double.valueOf(ltr[9].length()==0?"0":ltr[9]));
        djIndex.setPrevious_weeks_volume(Integer.valueOf(ltr[10].length()==0?"0":ltr[10]));
        djIndex.setNext_weeks_open(ltr[11]);
        djIndex.setNext_weeks_close(ltr[12]);
        djIndex.setPercent_change_next_weeks_price(Double.valueOf(ltr[13].length()==0?"0":ltr[13]));
        djIndex.setDays_to_next_dividend(Integer.valueOf(ltr[14].length()==0?"0":ltr[14]));
        djIndex.setPercent_return_next_dividend(Double.valueOf(ltr[15].length()==0?"0":ltr[15]));
        return djIndex;
    }

    public void save(DJIndex  djIndex){
        djIndexRepository.save(djIndex);
    }

    public List<DJIndex> getAllStocks() {
        return djIndexRepository.findAll();
    }
}
