<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class TaskModel extends Model
{
    protected $table = "tasks";
    public $timestamps = false;
    protected $fillable = [
        'startDate',
        'durationMinut',
        'title',
        'detail',
        'isDone',
        'user_Fk'
    ];
    use HasFactory;
}
