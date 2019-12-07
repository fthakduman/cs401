package provider.base.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import provider.base.model.RankRequest;
import provider.base.model.RankResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provider.base.util.CommonUtils;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/v1")
public class RankController {

    @Autowired
    RankCreator rankCreator;

    @GetMapping("/rank/{month}")
    public ResponseEntity<List<RankResponse>> getByMonth(@PathVariable String month) {
        List<String> banks = new ArrayList<String>();
        banks.add(CommonUtils.ykb);
        banks.add(CommonUtils.isb);
        banks.add(CommonUtils.dnz);
         rankCreator.setBankNames(banks);
         List<RankResponse> responses = new ArrayList<RankResponse>();
         List<RankResponse> rankResponses= rankCreator.getRank(2019, Month.valueOf(month));
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
    @PostMapping("/rank/{month}/{currencyDayOfMonthValue}")
    public ResponseEntity<List<RankResponse>> getByMonth(@RequestBody RankRequest request,@PathVariable String month, @PathVariable int currencyDayOfMonthValue) {
        List<String> banks = new ArrayList<String>();
        banks.add(CommonUtils.ykb);
        banks.add(CommonUtils.isb);
        banks.add(CommonUtils.dnz);
        rankCreator.setBankNames(banks);
        List<RankResponse> responses = new ArrayList<RankResponse>();
        List<RankResponse> rankResponses= rankCreator.getRank(2019, Month.valueOf(month),currencyDayOfMonthValue);
        return new ResponseEntity<List<RankResponse>>(rankResponses, HttpStatus.OK);
    }
//    @GetMapping("/rank/{month}/{currencyDayOfMonthValue}/{currencyHour}")
//    public ResponseEntity<List<YKBRateImpl>> getByMonth( @PathVariable String month,@PathVariable int currencyDayOfMonthValue,@PathVariable int currencyHour) {
//        Month q=null;
//        for(Month m: Month.values()){
//            if(m.name().equals(month)){
//                q=m;
//            }
//        }
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/lastweek")
//    public ResponseEntity<List<YKBRateImpl>> getByMonth( ) {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/lastday")
//    public ResponseEntity<List<YKBRateImpl>> getByMonth( int sdad) {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/last6h")
//    public ResponseEntity<List<YKBRateImpl>> getByMonths() {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/last12h")
//    public ResponseEntity<List<YKBRateImpl>> getByLast12H() {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }
//    @GetMapping("/rank/last18h")
//    public ResponseEntity<List<YKBRateImpl>> getByLast18H() {
//        Month q=null;
//        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q).getContent();
//        if (userImpls.isEmpty()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//            // You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
//    }


}