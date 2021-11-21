<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;
class CreateClientsTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('clients', function(Blueprint $table)
		{
			$table->increments('clientID');
			$table->string('fName', 100)->nullable();
			$table->string('lName', 100)->nullable();
			$table->dateTime('birthDate')->default('1980-08-03 01:00:00');
			$table->string('photo', 50)->nullable();
			$table->enum('identityDoc', array('CINE','EPORT','SEJOUR',''))->nullable();
			$table->string('identityNumber', 30)->nullable();
			$table->timestamp('inscriptionDate')->default(DB::raw('CURRENT_TIMESTAMP'));
			$table->dateTime('ensurenceValidity')->default('0000-00-00 00:00:00');
			$table->dateTime('licenceValidity')->default('0000-00-00 00:00:00');
			$table->string('email', 100)->nullable();
			$table->string('password', 100)->nullable();
			$table->string('clientPhone', 15)->nullable();
			$table->smallInteger('priceRate')->unsigned();
			$table->boolean('isActive')->default(1);
			$table->text('notes');
		});
	}


	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('clients');
	}

}
