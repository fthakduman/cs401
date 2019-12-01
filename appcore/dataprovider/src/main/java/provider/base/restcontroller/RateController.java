package provider.base.restcontroller;


import collector.base.model.YKBRateImpl;
import provider.base.model.RateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/v1")
public class RateController {



    @Autowired
    RateImpl rate; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------


    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public ResponseEntity<List<YKBRateImpl>> listAllUsers() {
        List<YKBRateImpl> userImpls = rate.getYKBRates(2019);
        if (userImpls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
    }

    @GetMapping("/rate/{month}")
    public ResponseEntity<List<YKBRateImpl>> getByMonth( @PathVariable String month) {
        Month q=null;
     for(Month m: Month.values()){
         if(m.name().equals(month)){
             q=m;
         }
        }
        List<YKBRateImpl> userImpls = rate.getYKBRates(2019,q);
        if (userImpls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<YKBRateImpl>>(userImpls, HttpStatus.OK);
    }



}