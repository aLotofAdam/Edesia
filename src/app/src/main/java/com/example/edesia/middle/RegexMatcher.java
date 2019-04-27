package com.example.edesia.middle;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reformats the ingredients to be used with the OCR in order to compare the text detected to what
 * is actually in the database
 */
public class RegexMatcher {
    static String pattern;
    static String dupsPattern;

    public static List<String>PatternMatch(List<String> list) {
        Pattern replace = Pattern.compile("(tsp.)*(lb.)*(tbsp.)*(c.)*(oz.)*\\d*([,.])*");
        //regex to remove duplicate words from list
        Pattern dups = Pattern.compile("\"\\\\b(\\\\w+)(\\\\s+\\\\1\\\\b)+\";");
        pattern = "(\\btsp)(\\blb)(\\btbsp)(\\bc)(\\boz)(\\d)([\\.,])";
        dupsPattern = ("\"\\\\b(\\\\w+)(\\\\s+\\\\1\\\\b)+\";");

        String blank = "";
        StringBuilder sbList = new StringBuilder();
        for (String sList : list)
        {
            sbList.append(sList);
            sbList.append(",");
        }

        //System.out.println(sbList.toString());

        //System.out.println(list.replaceAll(dupsPattern));

        //Pattern pattern = Pattern.compile("\\w+");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = dups.matcher(sbList);

        // check all occurance
        /*while (matcher.find()) {
            //System.out.print("Start index: " + matcher.start());
            //System.out.print(" End index: " + matcher.end() + "");
            System.out.print(matcher.group());
            System.out.println(matcher.group(1));
           // list = matcher.replace(matcher.group(), matcher.group(1), "");
        }*/
        //System.out.println(matcher.replaceAll(matcher.group()));
        System.out.println(matcher);
       // ['extra-virgin olive oil' 'large white onion' 'clove of garlic' ' tomato paste' 'ground beef' 'chili powder' 'ground cumin' 'dried oregano' 'paprika' 'cayenne pepper' 'Kosher salt' 'Freshly ground black pepper' 'medium yellow onion' 'large carrots' 'stalks celery' 'baby potatoes' 'corned beef brisket' 'kosher salt' 'Freshly ground black pepper' ' paed piling spi' ' /sprigs thyme'][' /potatoes peeled' ' Kosher salt' ' /melted butter' ' /milk' ' sour eam' ' Freshly ground bla pepper' ' extra-virgin olive oil' ' large onion opped' ' rrots peeled and opped' ' /oves garli mind' ' fresh thyme' ' ground beef' ' frn peas' ' /frn rn' ' all-purpose flour'][' extra-virgin olive oil' ' small yellow onion did' ' jalapeño seeded and mind' ' oves garli mind' ' /oregano' ' /ground min' ' ( ) ns green ilies' ' bonelessskinless ien breasts t into thirds' ' low-sodium ien broth' ' Kosher salt' ' /Freshly ground bla pepper' ' /( ) ns white beans drained and rinsed' ' /frn rn' ' /sour eam'][' butter' ' /onion finely opped' ' red bell pepper finely opped' ' shrimp opped into / pies' ' garlioves mind' ' Cajun seasoning' ' lemon jui' ' Worstershire sau' ' Kosher salt' ' /eam eese softened' ' /sour eam' ' /shredded marella divided' ' /freshly shredded Parmesan'][' butter divided' ' extra-virgin olive oil' ' medium shrimp peeled and deveined' ' lemon thinlyslid plusjui of  lemon' ' oves garli mind' ' ushed red pepper flakes' ' Kosher salt'][' Kosher salt' ' /shrimp peeled and deveined' ' Jui of  limes' ' Jui of  orange' ' red onion did' ' jalapeños mind' ' /mber seeded and did' ' opped erry tomatoes' ' opped fresh lantro' ' mayonnaise'][' butter' ' /all-purpose flour' ' small yellow onion' ' medium green bell pepper opped' ' lery ribs opped' ' oves garli mind' ' andouille sausage slid into / pies' ' jun seasoning (without salt)' ' kosher salt' ' Freshly ground bla pepper' ' bay leaf' ' (-) n fire-roasted did tomatoes' ' ien broth'][' extra-virgin olive oil' ' bell peppers thinly slid' ' /onion thinly slid' ' Kosher salt' ' /Freshly ground bla pepper' ' /boneless skinlessien breasts slid into strips' ' /ili powder' ' ground min' ' dried oregano' ' medium flour tortillas' ' shredded Monterey ja' ' shredded eddar' ' avodo slid'][' fetuine' ' butter divided' ' shrimp peeled and deveined tails removed' ' kosher salt' ' Freshly ground bla pepper' ' oves garli mind' ' /all-purpose flour' ' heavy eam' ' whole milk' ' egg yolk'][' Kosher salt' ' wide ri noodles' ' lime jui' ' brown sugar' ' fish sau' ' /low-sodium soy sau' ' yenne pepper' ' vegetable oil' ' bell pepper thinly slid' ' oves garli mind' ' eggs lightly whisked' ' shrimp peeled and deveined' ' /Freshly ground bla pepper'][' extra-virgin olive oil' ' onionopped' ' bell peppers opped' ' Kosher salt' ' Freshly ground bla pepper' ' boneless skinless ien breasts t into  pies' ' dried oregano' ' andouille sausage slid' ' oves garli mind' ' tomato paste' ' low-sodium ien sto' ' (-) n ushed tomatoes' ' long grain ri' ' Old Bay seasoning'][' vegetable oil' ' oves garli mind' ' rrots peeled and finely opped' ' green bell pepper finely opped' ' shrimp peeled and deveined' ' oked white ri' ' frn peas defrosted' ' soy sau' ' sesame oil' ' large egg whisked' ' sriraa for serving'][' panko bread umbs' ' extra-virgin olive oil' ' garlipowder' ' Kosher salt' ' Freshly ground bla pepper' ' large eggs' ' all-purpose flour' ' raw shrimp peeled and deveined' ' Freshly opped lantro for garnish' ' mayonnaise'][' olive oil' ' /onion opped (about  p)' ' oves garli mind' ' bell peppers opped' ' /rn (frn and defrosted or nned)' ' /shrimp peeled and deveined' ' Cajun seasoning' ' kosher salt' ' Freshly ground bla pepper'][' extra-virgin olive oil' ' large onion opped' ' ground beef' ' garlioves mind' ' ta seasoning' ' kosher salt' ' /(-) n refried beans' ' water' ' large bag tortilla ips' ' shredded eddar' ' /Shredded Monterey ja' ' piled jalapeños' ' (-) n bla beans drained' ' avodo did' ' /la
                //'bread' 'rye bread' 'mustard' 'relish' 'deli corned beef' 'sauerkraut' 'berries' 'whole cloves' 'coriander seeds' 'extra-virgin olive oil' 'large white onion' 'clove of garlic' ' tomato paste' 'ground beef' 'chili powder' 'ground cumin' 'dried oregano' 'paprika' 'cayenne pepper' 'Kosher salt' 'Freshly ground black pepper' 'yellow onion' 'white onion' 'paprika' 'pickling spice' 'Crescent Rolls' 'cabbage leaves' 'Italian bread crumbs' 'red wine' 'corned beef' 'low-sodium beef broth' 'dried thyme' 'Swiss cheese' 'ketchup' 'large carrots' 'stalks celery' 'baby potatoes' 'corned beef brisket' 'kosher salt' 'bay leaves' 'thyme' 'sprigs thyme' 'russet potatoes' 'Kosher salt' 'butter' 'milk' 'sour cream' 'beef chuck stew meat' 'extra-virgin olive oil' 'large onion' 'provolone' 'rolls' 'boneless ribeye steaks' ' carrots' 'garlic powder' 'fresh thyme' 'ground beef' 'frozen peas' 'all-purpose flour' 'extra-virgin olive oil' 'yellow onion' 'jalapeño' 'cloves garlic' 'oregano' 'Cooking spray' 'grits' 'ground cumin' 'green lentils' 'boneless skinless chicken breasts' 'low-sodium chicken broth' ' Kosher salt' 'Freshly ground black pepper' 'can of white beans' 'frn rn' 'sour cream' 'butter' 'onion' 'red bell pepper' 'shrimp' 'garlic cloves' 'Cajun seasoning' 'lemon juice' 'Worstershire sauce' 'Kosher salt' 'cream cheese' 'sour cream' 'shredded mozzarella' 'shredded Parmesan' 'butter' 'extra-virgin olive oil' 'shrimp' 'lemon' 'cloves garlic' 'crushed red pepper flakes' 'Kosher salt' ' Kosher salt' 'cooked shrimp' 'limes' 'oranges' 'red onion' 'jalapeños' 'cucumber' 'cherry tomatoes' 'cilantro' 'mayonnaise' 'butter' 'all-purpose flour' 'yellow onion' 'green bell pepper' 'celery ribs' 'cloves garlic' 'andouille sausage' 'cajun seasoning' 'kosher salt' 'Freshly ground black pepper' 'bay leaf' 'fire-roasted diced tomatoes' 'chicken broth' 'extra-virgin olive oil' 'bell peppers' 'onion' 'Kosher salt' 'black pepper' 'boneless skinless chicken breasts' 'chili powder' 'ground cumin' 'dried oregano' 'flour tortillas' 'shredded Monterey jack' 'shredded cheddar' 'avocado' ' fetuine' 'butter divided' 'shrimp' 'kosher salt' 'ground black pepper' 'clove garlic' 'all-purpose flour' 'heavy cream' 'whole milk' 'egg' 'cabbage' 'rice noodles' 'lime juice' 'brown sugar' 'fish sauce' 'low-sodium soy sauce' 'cayenne pepper' 'vegetable oil' 'bell pepper' 'garlic cloves' 'eggs' 'shrimp' 'black pepper' 'extra-virgin olive oil' 'onion' 'bell peppers' 'Kosher salt' 'ground black pepper' 'boneless chicken breasts' 'oregano' 'sausage' 'cloves garlic' 'tomatoes' 'low-sodium chicken stock' 'crushed tomatoes' 'rice' 'Old Bay seasoning' 'vegetable oil' 'garlic' 'carrots' 'green bell pepper' 'white rice' 'peas' 'soy sauce' 'sesame oil' 'egg' 'sriracha' 'panko bread crumbs' 'extra-virgin olive oil' 'garlic powder' 'Kosher salt' 'ground black pepper' 'large eggs' 'flour' 'raw shrimp' 'Fresh cilantro' 'mayonnaise' 'olive oil' 'onion' 'cloves garlic' 'bell pepper' 'rn' 'shrimp' 'Cajun seasoning' 'kosher salt' 'Freshly ground black pepper' 'extra-virgin olive oil' 'large onion' 'ground beef' 'garlic cloves' ' ta seasoning' 'kosher salt' 'refried beans' 'water' 'tortilla chips' 'shredded cheddar' 'Shredded Monterey jack' 'jalapeños' 'black beans' 'avocado'
        // now create a new pattern and matcher to replace whitespace with tabs
        //Pattern replace = Pattern.compile("\\s+");
        //Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        //System.out.println(matcher2.replaceAll("\t"));

        return list;
    }
}
