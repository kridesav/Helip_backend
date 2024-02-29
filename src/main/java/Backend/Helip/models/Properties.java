package Backend.Helip.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "properties")
public class Properties {
    @Column(name = "properties_id")
    @JsonProperty("id")
    private Long id;
    @JsonProperty("katuosoite")
    private String katuosoite;
    @JsonProperty("kunta_nimi")
    private String kuntaNimi;
    @JsonProperty("kuntanumer")
    private Integer kuntanumer;
    @JsonProperty("lisatieto_")
    private String lisatieto;
    @JsonProperty("muokattu_v")
    private String muokattuV;
    @JsonProperty("nimi_fi")
    private String nimiFi;
    @JsonProperty("nimi_se")
    private String nimiSe;
    @JsonProperty("omistaja")
    private String omistaja;
    @JsonProperty("piste_json")
    private String pisteJson;
    @JsonProperty("postinumer")
    private String postinumer;
    @JsonProperty("postitoimi")
    private String postitoimi;
    @JsonProperty("puhelinnum")
    private String puhelinnum;
    @JsonProperty("sahkoposti")
    private String sahkoposti;
    @JsonProperty("sijainti_i")
    private Integer sijaintiI;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKatuosoite() {
        return katuosoite;
    }
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    public String getKuntaNimi() {
        return kuntaNimi;
    }
    public void setKuntaNimi(String kuntaNimi) {
        this.kuntaNimi = kuntaNimi;
    }
    public Integer getKuntanumer() {
        return kuntanumer;
    }
    public void setKuntanumer(Integer kuntanumer) {
        this.kuntanumer = kuntanumer;
    }
    public String getLisatieto() {
        return lisatieto;
    }
    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }
    public String getMuokattuV() {
        return muokattuV;
    }
    public void setMuokattuV(String muokattuV) {
        this.muokattuV = muokattuV;
    }
    public String getNimiFi() {
        return nimiFi;
    }
    public void setNimiFi(String nimiFi) {
        this.nimiFi = nimiFi;
    }
    public String getNimiSe() {
        return nimiSe;
    }
    public void setNimiSe(String nimiSe) {
        this.nimiSe = nimiSe;
    }
    public String getOmistaja() {
        return omistaja;
    }
    public void setOmistaja(String omistaja) {
        this.omistaja = omistaja;
    }
    public String getPisteJson() {
        return pisteJson;
    }
    public void setPisteJson(String pisteJson) {
        this.pisteJson = pisteJson;
    }
    public String getPostinumer() {
        return postinumer;
    }
    public void setPostinumer(String postinumer) {
        this.postinumer = postinumer;
    }
    public String getPostitoimi() {
        return postitoimi;
    }
    public void setPostitoimi(String postitoimi) {
        this.postitoimi = postitoimi;
    }
    public String getPuhelinnum() {
        return puhelinnum;
    }
    public void setPuhelinnum(String puhelinnum) {
        this.puhelinnum = puhelinnum;
    }
    public String getSahkoposti() {
        return sahkoposti;
    }
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }
    public Integer getSijaintiI() {
        return sijaintiI;
    }
    public void setSijaintiI(Integer sijaintiI) {
        this.sijaintiI = sijaintiI;
    }
    public String getTyyppiN1() {
        return tyyppiN1;
    }
    public void setTyyppiN1(String tyyppiN1) {
        this.tyyppiN1 = tyyppiN1;
    }
    public String getTyyppiN2() {
        return tyyppiN2;
    }
    public void setTyyppiN2(String tyyppiN2) {
        this.tyyppiN2 = tyyppiN2;
    }
    public String getTyyppiNim() {
        return tyyppiNim;
    }
    public void setTyyppiNim(String tyyppiNim) {
        this.tyyppiNim = tyyppiNim;
    }
    public Integer getTyyppikood() {
        return tyyppikood;
    }
    public void setTyyppikood(Integer tyyppikood) {
        this.tyyppikood = tyyppikood;
    }
    public String getWww() {
        return www;
    }
    public void setWww(String www) {
        this.www = www;
    }
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public String getYllapitaja() {
        return yllapitaja;
    }
    public void setYllapitaja(String yllapitaja) {
        this.yllapitaja = yllapitaja;
    }
    @JsonProperty("tyyppi_n_1")
    private String tyyppiN1;
    @JsonProperty("tyyppi_n_2")
    private String tyyppiN2;
    @JsonProperty("tyyppi_nim")
    private String tyyppiNim;
    @JsonProperty("tyyppikood")
    private Integer tyyppikood;
    @JsonProperty("www")
    private String www;
    @JsonProperty("x")
    private Double x;
    @JsonProperty("y")
    private Double y;
    @JsonProperty("yllapitaja")
    private String yllapitaja;
}