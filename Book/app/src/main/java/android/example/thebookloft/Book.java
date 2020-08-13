package android.example.thebookloft;

import android.util.Log;

public class Book {
    private String TitleForAction;
    private float RatingForAction;
    private String BookImageForAction;
    private String TitleForAdventure;
    private float RatingForAdventure;
    private String BookImageForAdventure;
    private String CostForAdventure;
    private String TitleForRomance;
    private float RatingForRomance;
    private String BookImageForRomance;
    private String Title;
    private float Rating;
    private String BookImage;
    private String ActionWebLink;
    private String AdventureWebLink;
    private String RomanceWebLink;
    private String WebLink;
    private String MysteryWebLink;
    private String ThrillerWebLink;
    private String SupernaturalWebLink;
    private String HorrorWebLink;
    private String TitleForHorror;
    private String TitleForSupernatural;
    private String TitleForMystery;
    private String TitleForThriller;
    private String BookImageForHorror;
    private String BookImageForSupernatural;
    private String BookImageForMystery;
    private String BookImageForThriller;

    public Book(String TitleForAction, String BookImageForAction, String TitleForAdventure, String BookImageForAdventure, String TitleForRomance, String BookImageForRomance, String ActionWebLink, String AdventureWebLink, String RomanceWebLink, String HorrorWebLink, String SupernaturalWebLink, String MysteryWebLink, String ThrillerWebLink, String BookImageForHorror, String BookImageForSupernatural, String BookImageForMystery, String BookImageForThriller
    , String TitleForHorror, String TitleForSupernatural, String TitleForMystery, String TitleForThriller) {
        this.TitleForAction = TitleForAction;
        this.TitleForAdventure = TitleForAdventure;
        this.TitleForRomance = TitleForRomance;
        this.BookImageForAction = BookImageForAction;
        this.BookImageForAdventure = BookImageForAdventure;
        this.BookImageForRomance = BookImageForRomance;
        this.ActionWebLink = ActionWebLink;
        this.AdventureWebLink = AdventureWebLink;
        this.RomanceWebLink = RomanceWebLink;
        this.ThrillerWebLink = ThrillerWebLink;
        this.MysteryWebLink  = MysteryWebLink;
    this.SupernaturalWebLink = SupernaturalWebLink;
    this.HorrorWebLink = HorrorWebLink;
    this.TitleForHorror = TitleForHorror;
    Log.v("Again", this.TitleForHorror);
    this.TitleForSupernatural = TitleForSupernatural;
    this.TitleForMystery = TitleForMystery;
    this.TitleForThriller = TitleForThriller;
    this.BookImageForHorror = BookImageForHorror;
    this.BookImageForMystery = BookImageForMystery;
    this.BookImageForSupernatural = BookImageForSupernatural;
    this.BookImageForThriller = BookImageForThriller;}

    public Book(String Title, String BookImage, String WebLink) {
        this.BookImage = BookImage;
        this.Title = Title;
        this.WebLink = WebLink;
    }

    public String getTitleForRomance() {
        return TitleForRomance;
    }

    public void setTitleForRomance(String titleForRomance) {
        TitleForRomance = titleForRomance;
    }

    public float getRatingForRomance() {
        return RatingForRomance;
    }

    public void setRatingForRomance(float ratingForRomance) {
        RatingForRomance = ratingForRomance;
    }

    public String getBookImageForRomance() {
        return BookImageForRomance;
    }

    public void setBookImageForRomance(String bookImageForRomance) {
        BookImageForRomance = bookImageForRomance;
    }

    public String getCostForRomance() {
        return CostForRomance;
    }

    public void setCostForRomance(String costForRomance) {
        CostForRomance = costForRomance;
    }

    private String CostForRomance;


    public void setTitleForAdventure(String titleForAdventure) {
        TitleForAdventure = titleForAdventure;
    }

    public void setRatingForAdventure(float ratingForAdventure) {
        RatingForAdventure = ratingForAdventure;
    }

    public void setBookImageForAdventure(String bookImageForAdventure) {
        BookImageForAdventure = bookImageForAdventure;
    }

    public void setCostForAdventure(String costForAdventure) {
        CostForAdventure = costForAdventure;
    }

    private String CostForAction;

    public String getTitleForAdventure() {
        return TitleForAdventure;
    }

    public float getRatingForAdventure() {
        return RatingForAdventure;
    }

    public String getBookImageForAdventure() {
        return BookImageForAdventure;
    }

    public String getCostForAdventure() {
        return CostForAdventure;
    }


    public String getCostForAction() {
        return CostForAction;
    }

    public void setCostForAction(String costForAction) {
        CostForAction = costForAction;
    }

    public String getBookImageForAction() {
        return BookImageForAction;
    }

    public void setBookImageForAction(String bookImageForAction) {
        BookImageForAction = bookImageForAction;
    }

    public float getRatingForAction() {
        return RatingForAction;
    }

    public void setRatingForAction(float ratingForAction) {
        RatingForAction = ratingForAction;
    }

    public String getTitleForAction() {
        return TitleForAction;
    }

    public void setTitleForAction(String titleForAction) {
        TitleForAction = titleForAction;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getActionWebLink() {
        return ActionWebLink;
    }

    public void setActionWebLink(String actionWebLink) {
        ActionWebLink = actionWebLink;
    }

    public String getAdventureWebLink() {
        return AdventureWebLink;
    }

    public String getRomanceWebLink() {
        return RomanceWebLink;
    }

    public String getWebLink() {
        return WebLink;
    }

    public String getHorrorWebLink() {
        return HorrorWebLink;
    }

    public String getSupernaturalWebLink() {
        return SupernaturalWebLink;
    }

    public String getMysteryWebLink() {
        return MysteryWebLink;
    }

    public String getThrillerWebLink() {
        return ThrillerWebLink;
    }

    public String getBookImageForHorror() {
        return BookImageForHorror;
    }

    public String getBookImageForMystery() {
        return BookImageForMystery;
    }

    public String getBookImageForSupernatural() {
        return BookImageForSupernatural;
    }

    public String getBookImageForThriller() {
        return BookImageForThriller;
    }

    public String getTitleForHorror() {
        return TitleForHorror;
    }

    public String getTitleForMystery() {
        return TitleForMystery;
    }

    public String getTitleForSupernatural() {
        return TitleForSupernatural;
    }

    public String getTitleForThriller() {
        return TitleForThriller;
    }
}
