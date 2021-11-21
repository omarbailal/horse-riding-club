<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class UserModel extends Model
{
    protected $table = "user";
    protected $fillable = [
        'email',
        'password',
        'adminLevel',
        'lastLoginTime',
        'isActive',
        'userFName',
        'userLName',
        'description',
        'userType',
        'userphoto',
        'contractDate',
        'userPhone',
        'displayColor',
    ];
    use HasFactory;
}
