<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

class CreateUserTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('user', function(Blueprint $table)
		{
			$table->smallInteger('userID', true)->unsigned();
			$table->string('email', 100)->nullable()->unique('email');
			$table->string('password', 100)->nullable();
			$table->boolean('adminLevel')->nullable();
			$table->timestamp('lastLoginTime')->default(DB::raw('CURRENT_TIMESTAMP'));
			$table->boolean('isActive')->default(1);
			$table->string('userFName', 100);
			$table->string('userLName', 100);
			$table->string('description', 100);
			$table->enum('userType', array('ADMIN','MONITOR','GUARD','SERVICE','OTHER','COMPTA'));
			$table->string('userphoto', 50)->default('default.jpg');
			$table->dateTime('contractDate')->default('0000-00-00 00:00:00');
			$table->string('userPhone', 15);
			$table->string('displayColor', 7)->default('#0000FF');
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('user');
	}

}
