
class EmbeddedDataSourceTest {

    @BeforeEach
    public void init(){
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
        .setType(H2)
        .addScript("schema.sql")
        .addScript("data.sql")
        .build();
    }


}