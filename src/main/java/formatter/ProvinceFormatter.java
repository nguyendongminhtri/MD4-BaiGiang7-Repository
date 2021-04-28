package formatter;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.IProvinceService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ProvinceFormatter implements Formatter<Province> {
    private IProvinceService provinceService;
    @Autowired
    public  ProvinceFormatter(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> province = provinceService.findById(Long.parseLong(text));
        return province.orElse(null);
    }

    @Override
    public String print(Province province, Locale locale) {
        return "["+province.getId()+","+province.getName()+"]";
    }
}
