package chun.li.GStack.Http.DSL.Arrange;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.apache.calcite.linq4j.ExtendedEnumerable;
import org.apache.calcite.linq4j.Grouping;

import static chun.li.GStack.Http.Core.buildRequest;
import static org.apache.calcite.linq4j.Linq4j.asEnumerable;

public class Uri {
    @Step("BASE <baseUri>")
    public void baseURI(String baseUri) {
        buildRequest().baseUri(baseUri);
    }

    @Step("PATH <basePath>")
    public void basePath(String basePath){
        buildRequest().basePath(basePath);
    }

    @Step("QUERY <table>")
    public void queries(Table table) {
        buildRequest().queryParams(
                asEnumerable(table.getTableRows())
                        .groupBy(
                                row->row.getCell("name"),
                                r->r.getCell("value"))
                        .toMap(Grouping::getKey, ExtendedEnumerable::toList)
        );
    }

    @Step("QUERY <name> <value>")
    public void query(String name, String value) {
        buildRequest()
                .queryParam(name, value);
    }
}
