<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ClientModel extends Model
{
    protected $table = "clients";
    public $timestamps = false;
    protected $fillable = [
        'fName',
        'lName',
        'birthDate',
        'photo',
        'identityDoc',
        'identityNumber',
        'inscriptionDate',
        'ensurenceValidity',
        'licenceValidity',
        'email',
        'password',
        'clientPhone',
        'priceRate',
        'isActive',
        'notes'
    ];
    use HasFactory;
}
