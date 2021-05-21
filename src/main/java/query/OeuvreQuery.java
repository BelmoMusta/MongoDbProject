package query;

import documents.Oeuvre;

public class OeuvreQuery extends AbstractQuery<Oeuvre> {
    @Override
    protected String getNomCollection() {
        return Oeuvre.NOM_COLLECTION;
    }

    @Override
    protected Class<Oeuvre> getaClass() {
        return Oeuvre.class;
    }
}
