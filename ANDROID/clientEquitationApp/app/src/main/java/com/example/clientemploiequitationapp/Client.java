package com.example.clientemploiequitationapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Client implements Serializable {
    int id;
    String fName, lName, photo, identityNumber, email, password, Phone, notes;
    Date birthDate, inscriptionDate, ensurenceDate, licenceValidity;
    int priceRate;
    double isActive;
    ArrayList<Seance> seances;

    public Client() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getInscriprionDate() {
        return inscriptionDate;
    }

    public void setInscriprionDate(Date inscriprionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public Date getEnsurenceDate() {
        return ensurenceDate;
    }

    public void setEnsurenceDate(Date ensurenceDate) {
        this.ensurenceDate = ensurenceDate;
    }

    public Date getLicenceValidity() {
        return licenceValidity;
    }

    public void setLicenceValidity(Date licenceValidity) {
        this.licenceValidity = licenceValidity;
    }


    public int getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(int priceRate) {
        this.priceRate = priceRate;
    }

    public double getIsActive() {
        return isActive;
    }

    public void setIsActive(double isActive) {
        this.isActive = isActive;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }

    public void  addSeance(Seance s){
        if(seances == null)
            seances = new ArrayList<>();
        seances.add(s);
    }
    public Seance getSeanceById(int seanceID){
        ArrayList<Seance> seances = this.getSeances();
        for(int i = 0; i < seances.size(); i++){
            if(seances.get(i).getId() == seanceID)
                return seances.get(i);
        }
        return null;
    }
}
