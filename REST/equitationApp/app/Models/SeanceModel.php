<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SeanceModel extends Model
{
    protected $table = "seances";
    public $timestamps = false;
    protected $fillable = [
        'seanceGrpID',
        'clientID',
        'monitorID',
        'startDate',
        'durationMinut',
        'isDone',
        'paymentID',
        'comments'
    ];
    use HasFactory;
}
